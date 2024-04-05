package com.ventionteams.applicationexchange.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.resource.jdbc.spi.StatementInspector;

import java.util.regex.Pattern;

@Slf4j
public class SpecificationInterceptor implements StatementInspector {

    @Override
    public String inspect(String sql) {
        if (sql.contains("KEYWORD")) {
            var regex = "'KEYWORD'='(.*?)'";
            var pattern = Pattern.compile(regex);
            var matcher = pattern.matcher(sql);
            var keyword = "";
            if (matcher.find()) {
                keyword = matcher.group(1);
                if (keyword != null && !keyword.isBlank()) {
                    var searchInsert = "to_tsvector(c1_0.name || ' ' || l1_0.title || ' ' ||" +
                                       " l1_0.description) @@ to_tsquery('" + keyword + ":*')";
                    sql = sql.replaceFirst("'KEYWORD'='(.*?)'", searchInsert);
                } else {
                    sql = sql.replaceFirst("and 'KEYWORD'='(.*?)'", "");
                }
            } else {
                sql = sql.replaceFirst("and 'KEYWORD'='(.*?)'", "");
                log.error("There is no condition in search string");
            }
        }
        return sql;
    }
}
