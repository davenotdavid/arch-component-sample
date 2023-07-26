# Android Architecture Component Sample Project
A sample project attempting to use the latest and greatest Android tech. The code is divided into branches starting with [dev-arch-1](https://github.com/DaveNOTDavid/arch-component-sample/tree/dev-arch-1) with the main differences (for now) between that branch and say, [dev-arch-2](https://github.com/DaveNOTDavid/arch-component-sample/tree/dev-arch-2), being that `dev-arch-1` uses Dagger2 and RxJava2, whereas `dev-arch-2` uses Hilt and Kotlin Coroutines. Otherwise, here are the base technologies used for these dev branches so far:
- MVVM (with `LiveData` and `ViewModel`)
- Data/View Binding
- Navigation arch component, notably with Fragments
- Retrofit2
- OkHttp3
- Room DB
- Unit testing w/ Mockito

Note that the app is getting its data from [News API](https://newsapi.org).

Demo:

![alt tag](https://media.giphy.com/media/owSPoNvGV9k3ugESUh/giphy.gif)
