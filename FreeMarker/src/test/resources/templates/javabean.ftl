package ${packageName};
import java.util.Date;
public class ${className}{

<#-- 循环类型及属性 -->
<#list attrs as attr>
    private ${attr.type} ${attr.name}; //${attr.remarks}
</#list>

<#-- 循环生成set get方法 -->
<#list attrs as attr>
    public void set${attr.name}(${attr.type} ${attr.name}) {
    this.${attr.name} = ${attr.name};
    }
    public ${attr.type} get${attr.name}() {
    return ${attr.name};
    }
</#list>

}