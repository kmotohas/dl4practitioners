# Stop Explaining Black Box Machine Learning Models for High Stakes Decisions and Use Interpretable Models Instead
https://arxiv.org/abs/1811.10154

（実際読んだのはthe morning paperの解説記事 https://blog.acolyer.org/2019/10/28/interpretable-models/）

- explainable ML: DNNなどのblack box modelで予測を行い、LIMEなど別のモデルで解釈するアプローチ
- interpretable model: 推論モデル自体が解釈性を持つモデル

ただし画像認識におけるsaliency mapなどは推論理由を説明しているようでしていない。例えば犬の画像に対してリベリアンハスキーだと認識した証拠のマップと、フルートだと認識した証拠のマップは大して変わらない。

テーブルデータに対してIF-THEN-ELSEで構成される「モデル」を構築する手法としてはCORELS、RiskSLIMなどがある。画像分類にはChaofan Chen et alのThis Looks Like Thatなど。

著者の主張としてはfor certain high-stakes decisions, no black box should be deployed when there exists an interpretable model with the same level of performance.とある。経験則として、完全にblack boxなモデルではないと対処できないような問題はない。ハイリスクな問題に対しては解釈性のあるモデルを用いるべき。
