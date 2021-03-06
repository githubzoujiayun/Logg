package tool.log.logg;

import tool.log.logg.parser.BundleParse;
import tool.log.logg.parser.CollectionParse;
import tool.log.logg.parser.IntentParse;
import tool.log.logg.parser.MapParse;
import tool.log.logg.parser.Parser;
import tool.log.logg.parser.ReferenceParse;
import tool.log.logg.parser.ThrowableParse;

/**
 * 常量
 */
public class LogConstant {

    // Value is null
    public static final String NULL = "null";

    // 解析属性最大层级
    public static final int MAX_LEVEL = 2;

    // 换行符
    public static final String BR = System.getProperty("line.separator");

    // 每行最大日志长度
    public static final int LINE_MAX = 1024 * 3;

    // 默认支持解析库
    public static final Class<? extends Parser>[] DEFAULT_PARSER_CLASS = new Class[]{
            BundleParse.class, IntentParse.class, CollectionParse.class,
            MapParse.class, ThrowableParse.class, ReferenceParse.class
    };
}