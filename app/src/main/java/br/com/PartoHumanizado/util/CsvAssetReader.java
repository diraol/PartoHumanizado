package br.com.PartoHumanizado.util;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by bruno on 25/11/14.
 */
public abstract class CsvAssetReader extends AssetReader {

    private static final String TAG = "CsvAssetReader";

    String separatorRegex = ",";

    public CsvAssetReader(Context context, String fileName) {
        super(context, fileName);
    }

    public CsvAssetReader setSeparatorRegex(String separatorRegex) {
        this.separatorRegex = separatorRegex;
        return this;
    }

    @Override
    protected void readLine(String line) {
//        String[] split = line.split(separatorRegex);
        String[] strings = splitString(line, separatorRegex);

        readSplitedLine(strings);
    }

    public static String[] splitString(String line, String separatorRegex) {
        ArrayList<String> strings = new ArrayList<String>();

        int lineLength = line.length();
        int start = 0;
        int separetorIndex = 0;
        int separetorRegexLength = separatorRegex.length();
        while ((separetorIndex = line.indexOf(separatorRegex, start)) >= 0) {
//            System.out.printf("substring %s ~ %s\n", start, separetorIndex);
            String substring = line.substring(start, separetorIndex);
//            System.out.printf("colum \"%s\"\n",substring);
            strings.add(substring);
            start = separetorIndex + separetorRegexLength;
//            start = separetorIndex;
        }
        if (start <= lineLength) {
//            System.out.printf("substring %s ~ %s\n", start, separetorIndex);
            String substring = line.substring(start, lineLength);
//            System.out.printf("colum \"%s\"\n",substring);
            strings.add(substring);
            start = separetorIndex + separetorRegexLength;
//            start = separetorIndex;
        }
//        System.out.printf("start %s, index %s\n", start, separetorIndex);
        return strings.toArray(new String[strings.size()]);
    }

    abstract protected void readSplitedLine(String[] split);


    public static void main(String[] args) {
        String[] strings = {
                "a;a;a",
                "b;b;b",
                ";c;c",
                "d;;d",
                "e;e;",
                "f;;"
        };

        System.out.println("begin");
        for (String line : strings) {
            String[] split = splitString(line, ";");
            System.out.printf("%s - %s\n", line, split.length);
            int i = 0;
            for (String s : split)
                System.out.printf(" -%s - %s\n", i++, s);
            System.out.println("");
        }
        System.out.println("end");
    }
}