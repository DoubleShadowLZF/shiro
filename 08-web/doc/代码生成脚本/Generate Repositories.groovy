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
packageName = "${projectPath}.${projectName}.dao;"
qoPackagePath = "${projectPath}.${projectName}.entity.qo"
dtoPackagePath = "${projectPath}.${projectName}.entity.dto"
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
    def dtoEntityClassName = javaName(table.getName()+"Dto", true)
    def qoEntityClassName = javaName(table.getName()+"Qo", true)
    def className = javaName(table.getName(), true) + "Repository"
    def fields = calcFields(table)
    new File(dir, className + ".java").withPrintWriter("UTF-8") {
        out -> generate(out, className, dtoEntityClassName,qoEntityClassName, table.getName())
    }
}

def generate(out, className, dtoEntityClassName,qoEntityClassName, tableName) {
    def beanName = Case.LOWER.apply(className.substring(0, 1)) + className.substring(1)

    out.println "package $packageName"
    out.println ""
    out.println "import ${dtoPackagePath}.$dtoEntityClassName;"
    out.println "import ${qoPackagePath}.${qoEntityClassName};"
    out.println "import org.springframework.data.jpa.repository.JpaRepository;"
    out.println "import org.springframework.data.jpa.domain.Specification;"
    out.println "import org.springframework.data.jpa.repository.JpaSpecificationExecutor;"
    out.println "import org.springframework.stereotype.Repository;"
    out.println ""
    out.println "import javax.persistence.criteria.Predicate;"
    out.println "/**"
    out.println " * the service class of $dtoEntityClassName can do CRUD for the database. "
    out.println " * "
    out.println " */"
    out.println "@Repository(value = \"$beanName\")"
    out.println "public interface $className extends JpaRepository<$dtoEntityClassName, Long>, JpaSpecificationExecutor<$dtoEntityClassName> {"
    out.println ""
    out.println "	default Specification<$dtoEntityClassName> build(${qoEntityClassName} qo) {"
    out.println "		return ((root, query, cb) -> {"
    out.println "			Predicate where = null;"
    out.println "			where = cb.and(cb.equal(root.get(\"isDeleted\"),0));"
    out.println ""
    out.println "			if(qo.getId() != null){"
    out.println "				where = cb.and(where,cb.equal(root.get(\"id\"),qo.getId()));"
    out.println "			}"
    out.println "			return query.where(where).getRestriction();"
    out.println "		});"
    out.println "	}"
    out.println ""
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