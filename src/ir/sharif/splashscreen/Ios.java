package ir.sharif.splashscreen;

import secondPhase.MainPage;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Ios extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View ios = inflater.inflate(R.layout.ios_frag, container, false);
		((TextView) ios.findViewById(R.id.textView)).setText("90104413");
		TextView tv = (TextView) ios.findViewById(R.id.textView1);
		
		tv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				final Intent mainIntent = new Intent(getActivity(),
						MainPage.class);
				getActivity().startActivity(mainIntent);
				getActivity().finish();

			}
		});
		
		return ios;
	}
}