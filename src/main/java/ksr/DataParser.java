package ksr;

import java.io.IOException;
import java.util.List;

public interface DataParser<Data> {
    List<Data> parse(String path) throws IOException;
}
