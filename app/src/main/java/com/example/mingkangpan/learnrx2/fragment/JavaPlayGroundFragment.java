package com.example.mingkangpan.learnrx2.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mingkangpan.learnrx2.HelperKt;
import com.example.mingkangpan.learnrx2.R;
import com.example.mingkangpan.learnrx2.RxFragment;

import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by mingkangpan on 17/04/2017.
 */

public class JavaPlayGroundFragment extends RxFragment {

	private final View.OnClickListener onClickListener1 = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			test1();
		}
	};

	private final View.OnClickListener onClickListener2 = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			test2();
		}
	};

	private final View.OnClickListener onClickListener3 = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			test3();
		}
	};

	private final View.OnClickListener onClickListener4 = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			test4();
		}
	};

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_playground_java, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		getView().findViewById(R.id.btn_fragment_playground_1).setOnClickListener(onClickListener1);
		getView().findViewById(R.id.btn_fragment_playground_2).setOnClickListener(onClickListener2);
		getView().findViewById(R.id.btn_fragment_playground_3).setOnClickListener(onClickListener3);
		getView().findViewById(R.id.btn_fragment_playground_4).setOnClickListener(onClickListener4);
	}

	private void test1(){
		Observable.fromArray(1,2,3,4,5,6,7,8,9,10)
				.subscribe(new Consumer<Integer>() {
					@Override
					public void accept(@NonNull Integer integer) throws Exception {
						HelperKt.logWithCurrentThread(JavaPlayGroundFragment.this, String.format(Locale.getDefault(), "Next Int: %d", integer));
					}
				});
	}

	private void test2(){
		Observable.fromArray(1,2,3,4,5,6,7,8,9,10)
				.subscribe(new Consumer<Integer>() {
					@Override
					public void accept(@NonNull Integer integer) throws Exception {
						HelperKt.logWithCurrentThread(JavaPlayGroundFragment.this, String.format(Locale.getDefault(), "Next Int: %d", integer));
					}
				}, new Consumer<Throwable>() {
					@Override
					public void accept(@NonNull Throwable throwable) throws Exception {

					}
				});
	}

	private void test3(){
		Observable.fromArray(1,2,3,4,5,6,7,8,9,10)
				.subscribeWith(new Observer<Integer>() {
					@Override
					public void onSubscribe(Disposable d) {
						HelperKt.logWithCurrentThread(JavaPlayGroundFragment.this, "On Subscribed");
					}

					@Override
					public void onNext(Integer integer) {
						HelperKt.logWithCurrentThread(JavaPlayGroundFragment.this, String.format(Locale.getDefault(), "Next Int: %d", integer));
					}

					@Override
					public void onError(Throwable e) {

					}

					@Override
					public void onComplete() {
						HelperKt.logWithCurrentThread(JavaPlayGroundFragment.this, "OnComplete");
					}
				});
	}

	private void test4() {

	}
}
