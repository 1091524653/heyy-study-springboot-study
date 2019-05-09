package heyy.study.common.been;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @NotBlank(message = "{name.not.blank}")
    private String name;

    /**
     * idType = 1  idCard
     * idType = 0  temporary idCard
     */
    @NotBlank(message = "{idType.not.blank}")
    private String idType;
    @NotBlank(message = "{idNo.not.blank}")
    private String idNo;
    private int age;
    private int sex;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,ToStringStyle.JSON_STYLE);
    }
}
