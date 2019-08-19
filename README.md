# todo_app_sts

※早足で開発したためコードや構成が汚く，見にくいと思います．  
またSpringBootについても初学者のため設計など解釈しづらいこともあるかもしれません．
申し訳ないです．  
なお，時間があまりなかったのでgitを使わず開発し最後に全部突っ込んだためlog等残ってないです．

## 使用した技術要素

言語
- Java
- html
- css
- javascript

フレームワーク
- SpringBoot

開発環境
- Spring Tool Suite 4 (STS4)

データベース
- MySQL

## 全体の設計・構成

- todo_db.sql: todoAppで用いるmysqlをエクスポートしたもの
- todoApp: todoアプリ本体
  - index.html: トップ画面
  - edit.html: 編集画面
  - searchpage.html: 検索画面
  - header.html: 共通ヘッダー

## 開発環境のセットアップ手順

1. このレポジトリを clone  
`git clone https://github.com/Jum-99/todo_app_sts.git`
2. clone したディレクトリに入る  
`cd todo_app_sts`
3. mysqlを起動し，データベースをインポート  
`mysql -u testuser -p todo_db < todo_db.sql`  
パスワードは `testuser`
4. STS4で `todoApp` フォルダをプロジェクトとして開く
5. STS4 でrun
6. `localhost:8080` にアクセスするとtodoアプリが開ける

## まだできていないこと

バグ
- 編集によってtodoの更新ができない（新規作成されてしまう）

手付かず
- 検索機能の実装
- デザインの整形
- Todoが空のときに表示するメッセージ
- Todoのタイトルに特殊文字を含む場合のエスケープ

上記以外は仕様書の内容を満たしていると思います
