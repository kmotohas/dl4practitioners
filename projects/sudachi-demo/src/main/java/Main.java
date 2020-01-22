import com.worksap.nlp.sudachi.Dictionary;
import com.worksap.nlp.sudachi.DictionaryFactory;
import com.worksap.nlp.sudachi.Morpheme;
import com.worksap.nlp.sudachi.Tokenizer;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String args[]) throws IOException{

        String settings_path = Main.class.getClassLoader().getResource("sudachi.json").getPath();
        FileInputStream input = new FileInputStream(settings_path);
        String settings = readAll(input);
        Dictionary dict = new DictionaryFactory().create("src/main/resources", settings);
        Tokenizer tokenizer = dict.create();
        Tokenizer.SplitMode mode = Tokenizer.SplitMode.C;
        String text = "自然言語処理（しぜんげんごしょり、英語: natural language processing、略称：NLP）は、人間が日常的に使っている自然言語をコンピュータに処理させる一連の技術であり、人工知能と言語学の一分野である。";

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            for (Morpheme m : tokenizer.tokenize(mode, text)) {
                //System.out.println(m.surface());
            }
        }
        long endTime = System.currentTimeMillis();

        System.out.println("start time: " + startTime);
        System.out.println("end time: " + endTime);
        System.out.println("elapsed time: " + (endTime - startTime) + " ms");

    }

    static String readAll(InputStream input) throws IOException {
        InputStreamReader isReader = new InputStreamReader(input, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isReader);
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            sb.append(line);
        }
        return sb.toString();
    }

    /*
    static void run(Tokenizer tokenizer, Tokenizer.SplitMode mode, InputStream input, PrintStream output,
                    boolean printAll, boolean ignoreError) throws IOException {

        try (InputStreamReader inputReader = new InputStreamReader(input);
             BufferedReader reader = new BufferedReader(inputReader)) {

            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                try {
                    for (Morpheme m : tokenizer.tokenize(mode, line)) {
                        output.print(m.surface());
                        output.print("\t");
                        output.print(String.join(",", m.partOfSpeech()));
                        output.print("\t");
                        output.print(m.normalizedForm());
                        if (printAll) {
                            output.print("\t");
                            output.print(m.dictionaryForm());
                            output.print("\t");
                            output.print(m.readingForm());
                            output.print("\t");
                            output.print(m.getDictionaryId());
                            if (m.isOOV()) {
                                output.print("\t");
                                output.print("(OOV)");
                            }
                        }
                        output.println();
                    }
                    output.println("EOS");
                } catch (RuntimeException e) {
                    if (ignoreError) {
                        //logger.warning(e.getMessage() + "\n");
                    } else {
                        throw e;
                    }
                }
            }
        }
    }
    */
}
