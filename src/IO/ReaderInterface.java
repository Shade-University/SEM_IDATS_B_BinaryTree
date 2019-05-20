package IO;

import java.io.IOException;

/**
 *
 * @author user
 */
public interface ReaderInterface {

    public Integer readChar() throws IOException;

    public String readLine() throws IOException;

    public String readAll() throws IOException;

}
