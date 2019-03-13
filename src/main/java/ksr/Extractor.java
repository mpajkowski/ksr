package ksr;

import java.io.IOException;
import java.util.List;

public interface Extractor<Data> {
    List<Data> extract(String path) throws IOException;
}
