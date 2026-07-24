

public class PlaylistLinkedList {
    private PlayListNode head;

    public PlaylistLinkedList() {
        this.head = null;
    }

    // 依代碼搜尋
    public PlayListNode search(String code) {
        PlayListNode current = head;
        while (current != null) {
            if (current.code.equalsIgnoreCase(code)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    // 尾端新增（防重複代碼）
    public boolean addSong(String code, String name) {
        if (search(code) != null) {
            System.out.println("【新增失敗】歌曲代碼已存在: " + code);
            return false;
        }

        PlayListNode newNode = new PlayListNode(code, name);
        if (head == null) {
            head = newNode;
        } else {
            PlayListNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("【成功新增】[" + code + "] " + name);
        return true;
    }

    // 依代碼刪除（正確處理第一首與最後一首）
    public boolean removeSong(String code) {
        if (head == null) {
            System.out.println("【刪除失敗】播放清單為空。");
            return false;
        }

        // 刪除第一首 (head)
        if (head.code.equalsIgnoreCase(code)) {
            System.out.println("【成功刪除第一首】[" + head.code + "] " + head.name);
            head = head.next;
            return true;
        }

        // 刪除中間或最後一首
        PlayListNode current = head;
        while (current.next != null && !current.next.code.equalsIgnoreCase(code)) {
            current = current.next;
        }

        if (current.next != null) {
            System.out.println("【成功刪除】[" + current.next.code + "] " + current.next.name);
            current.next = current.next.next;
            return true;
        }

        System.out.println("【刪除失敗】找不到代碼為 " + code + " 的歌曲。");
        return false;
    }

    // 列出完整播放順序
    public void printPlaylist() {
        if (head == null) {
            System.out.println("播放清單目前為空！");
            return;
        }
        System.out.println("=== 完整播放清單順序 ===");
        PlayListNode current = head;
        int index = 1;
        while (current != null) {
            System.out.println(index + ". [" + current.code + "] " + current.name);
            current = current.next;
            index++;
        }
        System.out.println("-----------------------");
    }
}