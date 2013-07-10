if ( 'prettyPrint' in window ) {} else {
    document.write( '<script type="text/javascript" src="http://gist-it.appspot.com/assets/prettify/prettify.js"></script>' );
}


document.write( '<link rel="stylesheet" href="http://gist-it.appspot.com/assets/embed.css"/>' );


document.write( '<link rel="stylesheet" href="http://gist-it.appspot.com/assets/prettify/prettify.css"/>' );

document.write( '<div class="gist-it-gist">\n<div class="gist-file">\n    <div class="gist-data">\n        \n        <pre class="prettyprint">package fr.frs.codestory.exercise1;\n\nimport java.util.Map;\nimport java.util.NavigableMap;\nimport java.util.TreeMap;\n\n/**\n * Solution to the FooBarQix problem.\n */\npublic class FooBarQix {\n\n    // NavigableMap keeps the order of appearance\n    private static final NavigableMap&lt;Integer, String&gt; FILTERS = new TreeMap&lt;Integer, String&gt;() {{\n        put(3, "Foo");\n        put(5, "Bar");\n        put(7, "Qix");\n    }};\n\n    public static String toFooBarQix(int n) {\n        String result = getFoobarqixFromDivisibilities(n);\n        result += getFoobarqixFromDigits(n);\n\n        if (result.isEmpty()) {\n            result = String.valueOf(n);\n        }\n        return result;\n    }\n\n    private static String getFoobarqixFromDigits(int n) {\n        String stringOfN = String.valueOf(n);\n        StringBuilder result = new StringBuilder();\n\n        for (char c : stringOfN.toCharArray()) {\n            int digit = Character.digit(c, 10);\n            if (FILTERS.containsKey(digit)) {\n                result.append(FILTERS.get(digit));\n            }\n        }\n        return result.toString();\n    }\n\n    private static String getFoobarqixFromDivisibilities(int n) {\n        StringBuilder result = new StringBuilder();\n        for (Map.Entry&lt;Integer, String&gt; filter : FILTERS.entrySet()) {\n            if (n % filter.getKey() == 0) {\n                result.append(filter.getValue());\n            }\n        }\n        return result.toString();\n    }\n\n}\n</pre>\n        \n    </div>\n    \n    <div class="gist-meta">\n        \n        <span><a href="https://github.com/fsarradin/code-story/blob/master/src/main/java/fr/frs/codestory/exercise1/FooBarQix.java">This Gist</a> brought to you by <a href="http://gist-it.appspot.com">gist-it</a>.</span>\n        \n        <span style="float: right; color: #369;"><a href="https://github.com/fsarradin/code-story/raw/master/src/main/java/fr/frs/codestory/exercise1/FooBarQix.java">view raw</a></span>\n        <span style="float: right; margin-right: 8px;">\n            <a style="color: rgb(102, 102, 102);" href="https://github.com/fsarradin/code-story/blob/master/src/main/java/fr/frs/codestory/exercise1/FooBarQix.java">FooBarQix.java</a></span>\n            <!-- Generated by: http://gist-it.appspot.com -->\n    </div>\n    \n</div>\n</div>' );

document.write( '<script type="text/javascript">prettyPrint();</script>' );