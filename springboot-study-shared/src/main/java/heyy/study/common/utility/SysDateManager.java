package heyy.study.common.utility;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @Classname SysDateManager
 * @Description TODO
 * @Date 2019/5/9 14:06
 * @Created by Breeze
 */
@Slf4j
public class SysDateManager {

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static String getCurrentTime(String formatPattern){
        LocalDateTime localDateTime = LocalDateTime.now().atZone(ZoneOffset.of("+8")).toLocalDateTime();
        if(StringUtils.isBlank(formatPattern)){
            formatPattern = YYYY_MM_DD_HH_MM_SS;
        }
        DateTimeFormatter dateTimeFormatter = null;
        try {
            dateTimeFormatter = DateTimeFormatter.ofPattern(formatPattern);
        } catch (Exception e) {
            dateTimeFormatter = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);
            log.error("format date occur exception",e);
        }
        return dateTimeFormatter.format(localDateTime);
    }

    public static String getCurrentTime() {
        return getCurrentTime("");
    }
}
