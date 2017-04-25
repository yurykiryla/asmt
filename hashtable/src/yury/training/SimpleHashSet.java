package yury.training;

/**
 * Created by yuryk on 2017-04-25.
 */
public class SimpleHashSet<T> {
    private int[] head;
    private int[] next;
    private Key[] keys;
    private int headNum;
    private int cnt = 1;

    public SimpleHashSet(int headNum, int maxSize) {
        this.headNum = headNum;
        head = new int[headNum];
        next = new int[maxSize + 1];
        keys = new Key[maxSize + 1];
    }

    private static class Key<T> {
        private T key;

        public Key(T key) {
            this.key = key;
        }

        public T getKey() {
            return key;
        }

        public void setKey(T key) {
            this.key = key;
        }
    }
}
