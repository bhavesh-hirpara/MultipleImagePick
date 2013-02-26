# Smart Gallery with Single and Multiple image pick action.

## Features
 * luminous.ACTION_PICK for choosing single image.
 * luminous.ACTION_MULTIPLE_PICK for choosing multiple image.
 * Also custom Theme.

![Screenshot](https://raw.github.com/luminousman/MultipleImagePick/master/1.png)-
![Screenshot](https://raw.github.com/luminousman/MultipleImagePick/master/2.png)-
![Screenshot](https://raw.github.com/luminousman/MultipleImagePick/master/3.png)
 
## Usage

``` java
// For single image
Intent i = new Intent(Action.ACTION_PICK);
startActivityForResult(i, 100);

// For multiple images
Intent i = new Intent(Action.ACTION_MULTIPLE_PICK);
startActivityForResult(i, 200);

@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
			adapter.clear();

			viewSwitcher.setDisplayedChild(1);
			String single_path = data.getStringExtra("single_path");
			imageLoader.displayImage("file://" + single_path, imgSinglePick);

		} else if (requestCode == 200 && resultCode == Activity.RESULT_OK) {
			String[] all_path = data.getStringArrayExtra("all_path");

			ArrayList<CustomGallery> dataT = new ArrayList<CustomGallery>();

			for (String string : all_path) {
				CustomGallery item = new CustomGallery();
				item.sdcardPath = string;

				dataT.add(item);
			}

			viewSwitcher.setDisplayedChild(0);
			adapter.addAll(dataT);
		}
	}
```

In AndroidManifest.xml

``` xml

 <activity android:name="CustomGalleryActivity" >
            <intent-filter>
                <action android:name="luminous.ACTION_PICK" />
                <action android:name="luminous.ACTION_MULTIPLE_PICK" />

                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
 </activity>
```

