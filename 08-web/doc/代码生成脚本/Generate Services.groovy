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
projectPath = "org.middlesoft"
packageName = "${projectPath}.${projectName}.service"
implPackageName = "${projectPath}.${projectName}.service.impl;"
qoPackagePath = "${projectPath}.${projectName}.entity.qo"
dtoPackagePath = "${projectPath}.${projectName}.entity.dto"
repositoryPackagePath = "${projectPath}.${projectName}.dao"
typeMapping = [
        (~/(?i)int/)                      : "long",
        (~/(?i)float|double|decimal|real/): "double",
        (~/(?i)datetime|timestamp/)       : "java.sql.Timestamp",
        (~/(?i)date/)                     : "java.sql.Date",
        (~/(?i)time/)                     : "java.sql.Time",
        (~/(?i)/)                         : "String"
]

FILES.chooseDirectoryAndSave("Choose directory", "Choose where to store generated files") { dir ->
    SELECTION.filter { it instanceof DasTable }.each { generate(it, dir) }
}

def generate(table, dir) {
    def qoEntityClassName = javaName(table.getName() + "Qo", true)
    def dtoEntityClassName = javaName(table.getName() + "Dto", true)
    def className = javaName(table.getName(), true) + "Service"
    def implClassName = javaName(table.getName(), true) + "ServiceImpl"
    def repClassName = javaName(table.getName(), true) + "Repository"
    new File(dir, className + ".java").withPrintWriter {
        out -> generateServce(out, className, qoEntityClassName, dtoEntityClassName)
    }
    new File(dir, className + "Impl.java").withPrintWriter("UTF-8") {
        out -> generateServceImpl(out, implClassName, className, qoEntityClassName, dtoEntityClassName, repClassName)
    }
}

def generateServce(out, className, qoEntityClassName, dtoEntityClassName) {
    out.println "package $packageName"
    out.println ""
    out.println "import ${qoPackagePath}.$qoEntityClassName;"
    out.println "import ${dtoPackagePath}.$dtoEntityClassName;"
    out.println "import java.util.List;;"
    out.println ""
    out.println "/**"
    out.println " * "
    out.println " */"
    out.println "public interface $className {"
    out.println "//Define something special"
    out.println "    /**"
    out.println "     * insert a item "
    out.println "     * "
    out.println "    */"
    out.println "    int add($qoEntityClassName item);"
    out.println ""
    out.println ""
    out.println "    /**"
    out.println "     * delete a item "
    out.println "     * "
    out.println "    */"
    out.println "    int delete($qoEntityClassName item);"
    out.println ""
    out.println ""
    out.println "    /**"
    out.println "     * edit item "
    out.println "     * "
    out.println "    */"
    out.println "    int edit($qoEntityClassName item);"
    out.println ""
    out.println ""
    out.println "    /**"
    out.println "     * find items"
    out.println "     * "
    out.println "    */"
    out.println "    List<$dtoEntityClassName> find($qoEntityClassName item);"
    out.println "}"
}

def generateServceImpl(out, implClassName, className, qoEntityClassName, dtoEntityClassName,repClassName) {
    def beanName = Case.LOWER.apply(repClassName.substring(0, 1)) + repClassName.substring(1)
    out.println "package $implPackageName"
    out.println ""
    out.println "import ${qoPackagePath}.$qoEntityClassName;"
    out.println "import ${dtoPackagePath}.$dtoEntityClassName;"
    out.println "import ${repositoryPackagePath}.$repClassName;"
    out.println "import ${packageName}.$className;"
    out.println "import org.springframework.beans.factory.annotation.Autowired;"
    out.println "import org.springframework.stereotype.Service;"
    out.println ""
    out.println "/**"
    out.println " * "
    out.println " */"
    out.println "@Service"
    out.println "public class $implClassName implements $className {"
    out.println "    @Autowired"
    out.println "    private $repClassName $beanName;"
    out.println ""
    out.println "    /**"
    out.println "     * insert a item "
    out.println "     * "
    out.println "    */"
    out.println "    int add($qoEntityClassName item){"
    out.println "    return 0;"
    out.println "    }"
    out.println ""
    out.println "    /**"
    out.println "     * delete a item "
    out.println "     * "
    out.println "    */"
    out.println "    int delete($qoEntityClassName item){"
    out.println "    return 0;"
    out.println "    }"
    out.println ""
    out.println "    /**"
    out.println "     * edit item "
    out.println "     * "
    out.println "    */"
    out.println "    int edit($qoEntityClassName item){"
    out.println "    return 0;"
    out.println "    }"
    out.println ""
    out.println "    /**"
    out.println "     * find items"
    out.println "     * "
    out.println "    */"
    out.println "   List<$dtoEntityClassName> find($qoEntityClassName item){"
    out.println "    return null;"
    out.println "    }"
    out.println "}"
}

def calcFields(table) {
    DasUtil.getColumns(table).reduce([]) { fields, col ->
        def spec = Case.LOWER.apply(col.getDataType().getSpecification())
        def typeStr = typeMapping.find { p, t -> p.matcher(spec).find() }.value
        fields += [[
                           name : javaName(col.getName(), false),
                           type : typeStr,
                           annos: ""
                   ]]
    }
}

def javaName(str, capitalize) {
    def s = com.intellij.psi.codeStyle.NameUtil.splitNameIntoWords(str)
            .collect { Case.LOWER.apply(it).capitalize() }
            .join("")
            .replaceAll(/[^\p{javaJavaIdentifierPart}[_]]/, "_")
    capitalize || s.length() == 1 ? s : Case.LOWER.apply(s[0]) + s[1..-1]
}