package heyy.study.common.been;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @Classname ErrorDetails
 * @Description TODO
 * @Date 2019/5/9 13:42
 * @Created by Breeze
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
    private String timeStamp;
    private String details;
    private String requestBody;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,ToStringStyle.JSON_STYLE);
    }
}
