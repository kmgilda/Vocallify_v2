package trollers.com.vocallify_v2;


import android.os.Parcel;
import android.os.Parcelable;

public class WordsObject implements Parcelable {

    public String word, meaning;

    public static final Parcelable.Creator<WordsObject> CREATOR = new Creator<WordsObject>() {

        @Override
        public WordsObject[] newArray(int size) {
            // TODO Auto-generated method stub
            return new WordsObject[size];
        }

        @Override
        public WordsObject createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            return new WordsObject(source);
        }
    };

    private WordsObject(Parcel in){
        this.word=in.readString();
        this.meaning=in.readString();
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(word);
        dest.writeString(meaning);

    }

    public WordsObject(String w,String mean)
    {
        this.word = w;
        this.meaning=mean;
    }

    public String getW()
    {
        return word;
    }

    public String getM()
    {
        return meaning;
    }
}
