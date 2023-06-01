# My Contacts App
<h2>Orhan Uçar Ödev 8</h2>
 
<p>Bu örnek projede, Navigation Drawer Menu ile Kartvizit(Kişilerim) Uygulaması gerçekleştirlirmiştir. Kullanıcı yeni bir kişi ekleyebilir, eklediği kişileri görebilir, sibilir ve güncelleyebilir. Son eklenen on kullanıcı anasayfa da görünür. Kullanıcının kişiyi kayıt ederken seçtiği grup bilgisine göre Menu' den de sadece o grupta kayıtlı olan kullanıcıları görme şansına sahiptir. Uygulama da verileri tutmak için Room Database kullanılmıştır.</p>

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
  <img src=https://github.com/orhanucr/My-Contacts-App/assets/100219838/df860ee5-0116-43b7-a779-9bcd1e4ffe84 width="30%" />
  <img src=https://github.com/orhanucr/My-Contacts-App/assets/100219838/bbd0b061-1036-4ba6-9cb9-fa240439d9b2 width="30%" />
  <img src=https://github.com/orhanucr/My-Contacts-App/assets/100219838/af2e28e6-b9ab-456b-bacd-264f8d0f28eb width="30%" />
  <img src=https://github.com/orhanucr/My-Contacts-App/assets/100219838/781d2392-e558-4f63-82c5-2df682999270 width="30%" />
  <img src=https://github.com/orhanucr/My-Contacts-App/assets/100219838/caccf56f-7475-4435-a267-423af0d66302 width="30%" />
</p>


    
