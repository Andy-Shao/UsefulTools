package andy.shao.util;

public interface Convert<IN, OUT> {
  OUT convert(IN in);
}
