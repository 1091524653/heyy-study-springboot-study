package com.heyy.study.springbootstudycentral.been;

import com.heyy.study.springbootstudycentral.annotation.SensitiveFieldAnnotation;
import com.heyy.study.springbootstudycentral.constants.RegExpConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @SensitiveFieldAnnotation
    @NotBlank(message = "{name.not.blank}")
    @Pattern(regexp = RegExpConstants.REGEX_NAME)
    private String name;

    /**
     * idType = 1  idCard
     * idType = 0  temporary idCard
     */
    @NotBlank(message = "{idType.not.blank}")
    @Pattern(regexp = RegExpConstants.REGEX_ID_TYPE,message = "{idType.format.wrong}")
    private String idType;

    @SensitiveFieldAnnotation
    @NotBlank(message = "{idNo.not.blank}")
    private String idNo;

    @NotNull
    @Min(value = 18,message = "age should not less than 18")
    private int age;
    private String sex;
    @Email
    private String email;
    @NotNull(message = "address should not be null")
    @Valid
    private Address address;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,ToStringStyle.JSON_STYLE);
    }
}
