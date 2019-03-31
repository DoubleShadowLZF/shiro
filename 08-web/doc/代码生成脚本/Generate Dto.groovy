import com.intellij.database.model.DasTable
import com.intellij.database.util.Case
import com.intellij.database.util.DasUtil

/*
 * Available context bindings:
 *   SELECTION   Iterable<DasObject>
 *   PROJECT     project
 *   FILES       files helper
 */

projectName = "shiro" // 项目名称，用于包命名
packageName = "org.middlesoft.${projectName}.entity.dto;"
typeMapping = [
        (~/(?i)int/)                      : "Long",
        (~/(?i)float|double|decimal|real/): "Double",
        (~/(?i)datetime|timestamp/)       : "java.sql.Timestamp",
        (~/(?i)date/)                     : "java.sql.Date",
        (~/(?i)time/)                     : "java.sql.Time",
        (~/(?i)/)                         : "String"
]
dir = ''

FILES.chooseDirectoryAndSave("Choose directory", "Choose where to store generated files") { dir ->
    SELECTION.filter { it instanceof DasTable }.each { generate(it, dir) }
}

def generate(table, dir) {
    def className = javaName(table.getName() + "Dto", true)
    def fields = calcFields(table)
    dir = dir
    new File(dir, className + ".java").withPrintWriter("UTF-8") { out -> generate(out, className, fields, table.getName()) }
}

def generate(out, className, fields, tableName) {
    out.println "package $packageName"
    out.println "package $dir"
    out.println ""
    out.println "import lombok.Data;"
    out.println "import lombok.experimental.Accessors;"
    out.println ""
    out.println "import javax.persistence.Entity;"
    out.println "import javax.persistence.Id;"
    out.println "import javax.persistence.Table;"
    out.println ""
    out.println "/**"
    out.println " * the entity class of $tableName"
    out.println " */"
    out.println "@Data"
    out.println "@Accessors(chain = true)"
    out.println "@Entity"
    out.println "@Table(name = \"$tableName\")"
    out.println "public class $className {"
    fields.each() {
        out.println ""
        // 添加注解
        if (it.annos != "") {
            out.println "    ${it.annos}"
        }
        // 添加备注
        if (it.comment != "" && it.comment != null) {
            out.println "    /**"
            out.println "     * ${it.comment}"
            out.println "     */"
        }
        if (it.name == "id") {
            out.println "    @Id"
        }
        out.println "    private ${it.type} ${it.name};"
    }
    out.println ""
    out.println "}"
}

def calcFields(table) {
    DasUtil.getColumns(table).reduce([]) { fields, col ->

        def spec = Case.LOWER.apply(col.getDataType().getSpecification())
        def typeStr = typeMapping.find { p, t -> p.matcher(spec).find() }.value
        def field = [
                name   : javaName(col.getName(), false),
                type   : typeStr,
                comment: col.getComment(),
                annos  : ""
        ]
        fields += [field]

    }
}

def javaName(str, capitalize) {
    def s = com.intellij.psi.codeStyle.NameUtil.splitNameIntoWords(str)
            .collect { Case.LOWER.apply(it).capitalize() }
            .join("")
            .replaceAll(/[^\p{javaJavaIdentifierPart}[_]]/, "_")
    capitalize || s.length() == 1 ? s : Case.LOWER.apply(s[0]) + s[1..-1]
}

def isNotEmpty(content) {
    return content != null && content.toString().trim().length() > 0
}