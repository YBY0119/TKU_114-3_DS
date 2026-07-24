// 檔案名稱：PlayListSystem.java

public class PlayListSystem {
    public static void main(String[] args) {
        PlaylistLinkedList playlist = new PlaylistLinkedList();

        System.out.println("--- 1. 新增歌曲 ---");
        playlist.addSong("S01", "晴天");
        playlist.addSong("S02", "七里香");
        playlist.addSong("S03", "稻香");
        playlist.addSong("S04", "告白氣球");

        // 測試代碼重複
        System.out.println("\n--- 2. 測試重複新增 ---");
        playlist.addSong("S02", "重複的七里香");

        playlist.printPlaylist();

        System.out.println("\n--- 3. 測試搜尋 ---");
        PlayListNode found = playlist.search("S03");
        if (found != null) {
            System.out.println("找到歌曲: " + found.name);
        } else {
            System.out.println("未找到歌曲");
        }

        System.out.println("\n--- 4. 測試刪除第一首歌曲 (S01) ---");
        playlist.removeSong("S01");
        playlist.printPlaylist();

        System.out.println("\n--- 5. 測試刪除最後一首歌曲 (S04) ---");
        playlist.removeSong("S04");
        playlist.printPlaylist();

        System.out.println("\n--- 6. 測試刪除不存在的歌曲 (S99) ---");
        playlist.removeSong("S99");
    }
}