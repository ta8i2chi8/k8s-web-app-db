package com.example.helloworld.domain.entity;


// lombok:アノテーションを追加すると自動的にソースコードを生成してくれる
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// パラメータのないデフォルトコンストラクタを自動的に生成
@NoArgsConstructor
// 全てのフィールドを引数に持つコンストラクタを生成
@AllArgsConstructor
// @Getter,@Setter,@ToString,@EqualsAndHashCodeをまとめて使用
@Data
// Builderクラスを自動的に生成
@Builder
public class Hello {
    private String message;
}