import com.taozeyu.taolan.analysis.LexicalAnalysisException;
import com.taozeyu.taolan.analysis.SyntacticAnalysisException;
import com.taozeyu.taolan.intermediatecode.IntermediateCodeExpression;
import com.taozeyu.taolan.virtualMachine.VirtualMachine;
import org.apache.commons.io.FileUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Demo {
    public static void main(String[] args) throws IOException, SyntacticAnalysisException, IntermediateCodeExpression, LexicalAnalysisException {

        FileReader fileReader = new FileReader("/Users/hg/Github/DeepStudy2/compiler/TaolanTutorial-master/src/main/resources/hello.t");
        VirtualMachine.instance().run(fileReader);

    }
}
