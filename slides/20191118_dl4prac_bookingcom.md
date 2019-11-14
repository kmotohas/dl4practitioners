---
theme: "white"
transition: "slide"
slideNumber: true
highlightTheme: "monokai"
---

<!-- overwrite css-->
<style type="text/css">
    .reveal h1,
    .reveal h2,
    .reveal h3,
    .reveal h4,
    .reveal h5,
    .reveal h6 {
        text-transform: none;
    }
    .reveal {
        font-size: 200%;
    }
    .reveal ol li {
        font-size: 75%;
    }
    .reveal ul li {
        font-size: 75%;
    }
</style>

<!-- 発表時間目安30分+質問10分 -->

#### 論文解説
### 150 Successful Machine Learning Models: <br> 6 Lessons Learned at Booking.com
---
#### Konduit株式会社　本橋 和貴

@kmotohas

実践者向けディープラーニング勉強会　第8回　2019年11月18日

--

### どんな論文か

- 世界最大のオンライン旅行サイト Booking.com における約150の機械学習アプリ開発経験から学んだベストプラクティスの紹介
- 

※ [Gigazineの記事](https://gigazine.net/amp/20191009-machine-learning-booking-com)でだいたいわかる

--

### Booking.com

<!-- スクショ貼る -->

--

### 課題

- a

<!-- -->

--

### Booking.comで用いられているモデル

<!-- -->

--

### 6つのレッスン

> 1. 機械学習モデルを導入したプロジェクトは大きなビジネス価値をもたらす
> 2. 機械学習モデルのパフォーマンスは、ビジネスのパフォーマンスと同じにならない
> 3. 解決しようとする問題をはっきりさせることが大切
> 4. 予測は遅延をもたらす
> 5. モデルの質に関して迅速なフィードバックを得ること
> 6. ランダム化比較試験を用いてモデルを使った時のビジネスインパクトを測る

--

### 2.2 All model families can provide value:

--

### 3. Modeling: Offline Model Performance is just a Health Check

--

### 4. Modeling: Before Solving a Problem, Design It

--

### 5. Deployment: Time is Money

-- 

### 6. Monitoring: Unsupervised Red Flags

--

### 7. Evaluation: Experiment Design Sophistication Pays Off

--

### まとめ