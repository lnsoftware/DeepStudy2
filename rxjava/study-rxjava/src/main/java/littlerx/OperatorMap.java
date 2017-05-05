package littlerx;

/**
 * Created by iceGeneral on 2016/10/15.
 */
public final class OperatorMap<T, R> implements Operator<R, T> {

    final Func1<? super T, ? extends R> transformer;

    public OperatorMap(Func1<? super T, ? extends R> transformer) {
        this.transformer = transformer;
    }

    @Override
    public Subscriber<? super T> call(final Subscriber<? super R> o) {
        return new MapSubscriber<T, R>(o, transformer);
    }

    private class MapSubscriber<T, R> extends Subscriber<T> {

        private Subscriber<? super R> actual;
        private Func1<? super T, ? extends R> transformer;

        public MapSubscriber(Subscriber<? super R> o, Func1<? super T, ? extends R> transformer) {
            this.actual = o;
            this.transformer = transformer;
        }

        @Override
        public void onNext(T t) {
            R r = transformer.call(t);
            actual.onNext(r);
        }
    }

}
