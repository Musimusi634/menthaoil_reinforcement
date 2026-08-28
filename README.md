# Mentha Oil Reinforcement
[Mentha Oil mod](https://github.com/dice7000/menthaoil)で追加されるハッカ油の攻撃を強化します。  
動作には[Mentha Oil mod](https://github.com/dice7000/menthaoil)が必要です。当然ですよね？
  
具体的には、ハッカ油によるダメージはmintDamageを与え、mintdamageはgetHealthによって取得される値を最大体力に対する割合で減少させます。  
20以上のmintDamageを持つmodはisAlive及びisDeadOrDyingによって取得される値が置き換えられます。  
40以上のmintDamageを持つmodはisRemoved及びgetRemovalReasonによって取得される値が置き換えられます。  

簡単に言うとTheTrialMonolithのSoulDamageの劣化版です。
