

import javafx.beans.property.Property;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Test {
    public static void main(String[] args) throws IOException {
        CheckGroup checkGroup=new CheckGroup();
        List<CheckItem> list=new ArrayList<>();

        checkGroup.setId(1);
        checkGroup.setName("ss");
        checkGroup.setSex("dd");
        checkGroup.setRemark("dd2");
        checkGroup.setHelpCode("dioi");
        checkGroup.setCode("dd2");
        checkGroup.setAttention("dd");
        checkGroup.setCheckItems(list);
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("a.txt"));
        oos.writeObject(checkGroup);
        oos.close();

    }
}
