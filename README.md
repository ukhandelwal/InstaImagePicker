![Alt text](/../InstaImagePicker/imagePicker/src/main/res/drawable/screen_first.png?raw=true "")
![Alt text](/../InstaImagePicker/imagePicker/src/main/res/drawable/screen_two.png.png?raw=true "")

![Alt text](/../https://github.com/ukhandelwal/InstaImagePicker/tree/main/imagePicker/src/main/res/drawable/screen_two.png.png?raw=true "")




# Instagram Media Picker

Hello everyone, Welcome To this repo in this repo we do simply Instagram Media Picker via Photos and Videos.

## Installation

Implement the dependency in this [build.gradle](https://developer.android.com/studio/build) file.

```bash
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

```bash
dependencies {
     implementation 'com.github.ukhandelwal:InstaImagePicker:1.0.5'
}
```

## Usage

```python
import foobar

button?.setOnClickListener {
            startForResultLostItemFound.launch(Intent(this@MainActivity, Picker::class.java))
}

private val startForResultLostItemFound =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                var value = result.data?.getStringExtra("image")
                Log.d("image", value.toString())
            }
}
```
