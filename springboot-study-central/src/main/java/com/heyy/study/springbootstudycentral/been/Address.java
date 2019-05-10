package com.heyy.study.springbootstudycentral.been;

import com.heyy.study.springbootstudycentral.constants.RegExpConstants;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Classname Address
 * @Description TODO
 * @Date 2019/5/10 13:07
 * @Created by Breeze
 */
@Data
public class Address {
    @NotBlank(message = "{province.not.blank}")
    private String province;
    @NotBlank(message = "{city.not.blank}")
    private String city;
    /**
     * 1  --- homeAddress
     * 2  ---companyAddress
     * 3  ---Temporary Address
     */
    @NotBlank(message = "{addressType.not.blank}")
    @Pattern(regexp = RegExpConstants.REGEX_ADDRESS_TYPE,message = "{addressType.format.wrong}")
    private String addressType;
    @NotBlank(message = "{detailAddress.not.blank}")
    private String detailAddress;
}
