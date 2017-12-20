package org.opensolaris.opengrok.analysis;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.Analyzer;
import static org.apache.lucene.analysis.Analyzer.*;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.miscellaneous.WordDelimiterGraphFilter;
import static org.apache.lucene.analysis.miscellaneous.WordDelimiterGraphFilter.*;

public class ModHelper {

    public static TokenStreamComponents wrapTokenizer(Tokenizer src)
    {
        // Wrap Tokenizer with LowerCaseFilter, WordDelimiterGraphFilter to make search easier
        return new TokenStreamComponents(src,
                   new LowerCaseFilter(
                       new WordDelimiterGraphFilter(src,
                           GENERATE_WORD_PARTS | GENERATE_NUMBER_PARTS | SPLIT_ON_CASE_CHANGE | SPLIT_ON_NUMERICS, null)));
    }

    public static TokenStreamComponents getPathComponents()
    {
        return wrapTokenizer(new PathTokenizer());
    }
}
