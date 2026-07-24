

public class PlayListNode {
    String code; // 歌曲代碼
    String name; // 歌曲名稱
    PlayListNode next;

    public PlayListNode(String code, String name) {
        this.code = code;
        this.name = name;
        this.next = null;
    }
}