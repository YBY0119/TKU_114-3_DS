# 完整系統測試紀錄 (TestCases.md)

本文件包含系統之 15 個完整測試案例，涵蓋空資料、單筆、重複、邊界、找不到及復原操作。

| 案例編號 | 測試項目 | 輸入資料 | 操作步驟 | 預期結果 | 實際結果 | 測試狀態 |
| :---: | :--- | :--- | :--- | :--- | :--- | :---: |
| **TC-01** | 空資料搜尋 | 空清單 | 執行 Binary Search 查詢 "B001" | 回傳 `null` / 未找到 | 回傳 `null` | **PASS** |
| **TC-02** | 單筆資料排序 | 1 筆書籍資料 | 執行 Merge Sort | 清單保持原樣，不發生錯誤 | 正常執行且無錯誤 | **PASS** |
| **TC-03** | 正常新增資料 | `ID: R001, Name: Alice` | 呼叫 `register()` | 回傳成功，加入正式名單 | 成功加入正式名單 | **PASS** |
| **TC-04** | 重複編號防護 | `ID: R001` (已存在) | 呼叫 `register()` | 拒絕新增，顯示重複訊息 | 成功攔截重複編號 | **PASS** |
| **TC-05** | 名額滿移入 Queue | 額滿後新增 `R003` | 呼叫 `register()` | 加入候補 Queue | 成功進入 Queue 候補 | **PASS** |
| **TC-06** | 空 Queue 讀取 | 空 Queue | 呼叫 `peekNextPendingOrder()` | 提示佇列為空，回傳 `null` | 顯示提示，回傳 `null` | **PASS** |
| **TC-07** | 空 Stack 復原 | 空 Stack | 呼叫 `undoCancel()` | 提示 Stack 為空，回傳 `false` | 顯示提示，回傳 `false` | **PASS** |
| **TC-08** | 取消並自動遞補 | 取消正式名單成員 | 呼叫 `cancel("R001")` | R001 進入 Stack，Queue 遞補 | R001 移出，Queue 遞補 | **PASS** |
| **TC-09** | 復原取消 (Undo) | Stack 存有取消紀錄 | 呼叫 `undoCancel()` | 復原成功，重回名單 | 成功復原資料 | **PASS** |
| **TC-10** | 取消不存在資料 | `ID: R999` | 呼叫 `cancel("R999")` | 提示找不到資料，回傳 `false` | 回傳 `false` | **PASS** |
| **TC-11** | 二元搜尋第一筆 | 尋找編號最小者 | 執行 Binary Search | 成功找到 index 0 資料 | 成功找到 index 0 | **PASS** |
| **TC-12** | 二元搜尋最後一筆 | 尋找編號最大者 | 執行 Binary Search | 成功找到最後一筆資料 | 成功找到最後一筆 | **PASS** |
| **TC-13** | 二元搜尋不存在值 | `ID: B999` | 執行 Binary Search | 回傳 `null` | 回傳 `null` | **PASS** |
| **TC-14** | 線性搜尋不存在分類| `Category: Art` | 執行 Sequential Search | 回傳空清單 (size = 0) | 回傳 size = 0 清單 | **PASS** |
| **TC-15** | 穩定排序驗證 | 優先度相同之任務 | 執行 Merge Sort | 保持原先登記順序 | 順序保持一致 | **PASS** |

---

## 未通過項目與修正紀錄

於初始測試階段，曾發現以下 1 項問題並已完成修正：

* **失敗案例編號**：TC-04 (重複編號防護)
* **問題描述**：原先僅使用 `List.contains()` 進行檢查，未重寫 `equals()` 導致重複 ID 被成功新增。
* **修正內容**：引進 `Set<String> registeredIds` 機制，於新增時以 $O(1)$ 複雜度檢查 ID 是否已存在。
* **重新測試結果**：再次執行 TC-04，系統順利攔截重複編號，測試結果更新為 **PASS**。