# pikasan

jarファイル内の各クラスを走査し，それぞれのメソッド内で呼び出しているメソッドをcsvで取り出すプログラム．

## 実行方法

プログラムに jar ファイルを渡せば，結果を出力する．複数のjarファイルを指定しても良い．

```
$ java pikasan-1.0-SNAPSHOT.jar [OPTIONS] <JARS...>
OPTIONS
    --help:  print this message and exit.
JARS
    targets.
```

## 出力フォーマット．

出力される結果の各行は次のフォーマットになっている．

```
クラス名#メソッド名,メソッドの引数,呼び出しているメソッドの数,呼び出しているメソッド...
```

最初の２つの項目はどのメソッドなのかを特定するために利用する．
３つ目の項目は４項目目以降の数を表している．

## コンパイル方法

Maven を使ってコンパイルしてください．

```
$ ls
README.md    pom.xml      src/
$ mvn package
// 実行結果は省略．
$ ls
README.md    pom.xml      src/      target/
$ java -jar target/pikasan-1.0-SNAPSHOT.jar --help
OPTIONS
    --help:  print this message and exit.
JARS
    targets.
```

