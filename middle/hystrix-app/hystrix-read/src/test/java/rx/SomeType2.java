
package rx;

public class SomeType2 {
    private String value;

    public static void main(String[] args) {

        SomeType2 instance = new SomeType2();
        Observable<String> value = instance.valueObservable();
        instance.setValue("Some Value");
        value.subscribe(System.out::println);

    }

    public Observable<String> valueObservable() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override public void call(Subscriber<? super String> subscriber) {

                subscriber.onNext(value);
                subscriber.onCompleted();
            }
        });
    }


    public void setValue(String value) {
        this.value = value;
    }


}