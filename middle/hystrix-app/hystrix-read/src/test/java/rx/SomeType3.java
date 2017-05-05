
package rx;

import rx.functions.Func0;

public class SomeType3 {
    private String value;

    public static void main(String[] args) {

        SomeType3 instance = new SomeType3();
        Observable<String> value = instance.valueObservable();
        instance.setValue("Some Value");
        value.subscribe(System.out::println);

    }

    public Observable<String> valueObservable() {
        return Observable.defer(new Func0<Observable<String>>() {
            @Override public Observable<String> call() {
                return Observable.just(value);
            }
        });
    }

    public void setValue(String value) {
        this.value = value;
    }


}