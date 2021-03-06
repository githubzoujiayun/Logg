package tool.log.logg.parser;

import tool.log.logg.util.ObjectUtil;

import java.lang.ref.Reference;

/**
 * Reference
 */
public class ReferenceParse implements Parser<Reference> {

    @Override
    public Class<Reference> parseClassType() {
        return Reference.class;
    }

    @Override
    public String parseString(Reference reference) {
        Object actual = reference.get();
        StringBuilder builder = new StringBuilder(reference.getClass().getSimpleName() + "<"
                + actual.getClass().getSimpleName() + "> {");
        builder.append("→" + ObjectUtil.objectToString(actual));
        return builder.toString() + "}";
    }
}
