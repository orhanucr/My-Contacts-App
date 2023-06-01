# My Contacts App
<h2>Orhan Uçar Ödev 8</h2>
 
<p>Bu örnek projede, Navigation Drawer Menu ile Kartvizit(Kişilerim) Uygulaması gerçekleştirlirmiştir. Kullanıcı yeni bir kişi ekleyebilir, eklediği kişileri görebilir, silebilir ve güncelleyebilir. Son eklenen on kullanıcı anasayfa da görünür. Kullanıcının kişiyi kayıt ederken seçtiği grup bilgisine göre Menu' den de sadece o grupta kayıtlı olan kullanıcıları görme şansına sahiptir. Uygulama da verileri tutmak için Room Database kullanılmıştır.</p>

<h2>plugins</h2>
<pre><code>plugins {
    id 'com.android.application'
    id 'org.jetbrains.kotlin.android'
    id 'kotlin-kapt'
    }</code></pre>
    
<h2>dependencies</h2>

<pre><code>dependencies {

    def room_version = "2.5.1"
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    kapt "androidx.room:room-compiler:$room_version"
    implementation "androidx.room:room-ktx:$room_version"
    implementation 'androidx.core:core-ktx:1.7.0'
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.9.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.6.1'
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1'
    implementation 'androidx.navigation:navigation-fragment-ktx:2.4.1'
    implementation 'androidx.navigation:navigation-ui-ktx:2.4.1'
    implementation 'androidx.legacy:legacy-support-v4:1.0.0'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.5'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'}
    </code></pre>    

<h2>Uygulama Ekran Görüntüleri</h2>
<p float="left">
  <img src=https://github.com/orhanucr/My-Contacts-App/assets/100219838/0fa789b9-7849-46f8-9a5d-e12602b3398b width="30%" />
  <img src=https://github.com/orhanucr/My-Contacts-App/assets/100219838/9b49747b-a9f5-496c-901b-4b170da173e4 width="30%" />
  <img src=https://github.com/orhanucr/My-Contacts-App/assets/100219838/3fae8b5d-1a90-4bcc-b11b-a16b78e15225 width="30%" />
  <img src=https://github.com/orhanucr/My-Contacts-App/assets/100219838/4c7078d0-b4d6-449c-a4f2-3b8465f06c51 width="30%" />
  <img src=https://github.com/orhanucr/My-Contacts-App/assets/100219838/cf8e043b-f09b-4e87-a8fa-c813ebbd33ef width="30%" />

</p>



    
