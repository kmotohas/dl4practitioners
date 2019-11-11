# 150 successful machine learning models: 6 lessons learned at Booking.com

- [the morning paper](https://blog.acolyer.org/2019/10/07/150-successful-machine-learning-models/)
- [reddit](https://www.reddit.com/r/MachineLearning/comments/dexwmq/d_150_successful_machine_learning_models_6/)

KDD'19に採択されたBooking.comのMLOpsにおける事例レッスン集。
Gigazineにもまとめ記事あり。
https://gigazine.net/amp/20191009-machine-learning-booking-com

1. 機械学習モデルを導入したプロジェクトは大きなビジネス価値をもたらす
2. 機械学習モデルのパフォーマンスは、ビジネスのパフォーマンスと同じにならない
3. 解決しようとする問題をはっきりさせることが大切
4. 予測は遅延をもたらす
5. モデルの質に関して迅速なフィードバックを得ること
6. ランダム化比較試験を用いてモデルを使った時のビジネスインパクトを測る

(2), (4) のプロットが面白い。訓練時に使う評価メトリックとビジネスメトリックCTRなどは相関が低い、もしくはほぼない。モデルの推論速度とconversion rateには明確な逆相関。

## 関連文献

- [Applied machine learning at Facebook: a datacenter infrastructure perspective](https://blog.acolyer.org/2018/12/17/applied-machine-learning-at-facebook-a-datacenter-infrastructure-perspective/)
- [TFX: A TensorFlow-based production scale machine learning platform](https://blog.acolyer.org/2017/10/03/tfx-a-tensorflow-based-production-scale-machine-learning-platform/)