package secondPhase;

import ir.sharif.splashscreen.R;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.XLabels;
import com.github.mikephil.charting.utils.XLabels.XLabelPosition;
import com.github.mikephil.charting.utils.YLabels;
import com.github.mikephil.charting.utils.YLabels.YLabelPosition;

public class MainPage extends Activity {
	BarChart mChart;

	ActionBar actionBar;
	static int touched = 0;

	private SlidingDrawer drawer;
	private Button handle;
	// private Button clickMe;
	// private TextView text1;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_page);

		LinearLayout canvasLayout = (LinearLayout) findViewById(R.id.canvasview);
		canvasLayout.addView(new MyView(MainPage.this));
		// setContentView(new MyView(MainPage.this));

		actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.hide();

		// mohem *****************
		initializeDrawer();
		// chartInitialization();

		View l = getWindow().getDecorView();

		// RelativeLayout l = (RelativeLayout ) findViewById(R.id.layout);
		l.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				int x = (int) arg1.getX();
				int y = (int) arg1.getY();

				String upordown = upOrDown(x, y);

				if (upordown == null)
					return false;

				if (touched == 0 && upordown.equals("up")) {
					touched = 1;
					// Create custom dialog object
					final Dialog dialog = new Dialog(MainPage.this);
					// Include dialog.xml file
					dialog.setContentView(R.layout.custom_dialog);
					// Set dialog title
					dialog.setTitle("Price");

					EditText etnumber = (EditText) dialog
							.findViewById(R.id.editText1);

					dialog.setCancelable(false);

					dialog.show();

					Button declineButton = (Button) dialog
							.findViewById(R.id.button1);
					declineButton.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							touched = 0;
							// Close dialog
							dialog.dismiss();

						}
					});
					// Toast.makeText(MainActivity.this,"asli :"+width+ "x : " +
					// x
					// +"asli : "+height+ " y : " + y,
					// Toast.LENGTH_SHORT).show();
				}

				if (touched == 0 && upordown.equals("down")) {
					touched = 1;
					// Create custom dialog object
					final Dialog dialog = new Dialog(MainPage.this);
					// Include dialog.xml file
					dialog.setContentView(R.layout.custom_dialog);
					// Set dialog title
					dialog.setTitle("Price");

					EditText etnumber = (EditText) dialog
							.findViewById(R.id.editText1);

					dialog.setCancelable(false);

					dialog.show();

					Button declineButton = (Button) dialog
							.findViewById(R.id.button1);
					declineButton.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							touched = 0;
							// Close dialog
							dialog.dismiss();

						}
					});
					// Toast.makeText(MainActivity.this,"asli :"+width+ "x : " +
					// x
					// +"asli : "+height+ " y : " + y,
					// Toast.LENGTH_SHORT).show();
				}

				return false;
			}
		});

	}

	private void initializeDrawer() {
		context = this.getApplicationContext();

		handle = (Button) findViewById(R.id.handle);
		// text1 = (TextView) findViewById(R.id.text1);
		// clickMe = (Button) findViewById(R.id.click);
		drawer = (SlidingDrawer) findViewById(R.id.slidingDrawer);

		drawer.setOnDrawerOpenListener(new OnDrawerOpenListener() {
			@Override
			public void onDrawerOpened() {
				handle.setText("-");
				 chartInitialization();

				// text1.setText("Already dragged...");
			}
		});

		drawer.setOnDrawerCloseListener(new OnDrawerCloseListener() {

			@Override
			public void onDrawerClosed() {
				handle.setText("+");
				// text1.setText("For more info drag the button...");
			}
		});

		// clickMe.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// Toast.makeText(context, "The button is clicked",
		// Toast.LENGTH_LONG).show();
		// }
		// });
	}

	public String upOrDown(int x, int y) {
		DisplayMetrics displayMetrics = MainPage.this.getResources()
				.getDisplayMetrics();
		int width = displayMetrics.widthPixels;
		int height = displayMetrics.heightPixels;

		float s = (float) y / height;
		// Toast.makeText(MainActivity.this, s + "", Toast.LENGTH_SHORT)
		// .show();

		int distance = (y - height / 2) * (y - height / 2) + (x - width / 2)
				* (x - width / 2);

		// Toast.makeText(MainActivity.this,
		// "distance : " + distance + " y/height : " + s,
		// Toast.LENGTH_SHORT).show();

		if (s < 0.5) {

			if (distance < 200 * 200 && distance > 100 * 100) {
				Toast.makeText(MainPage.this, "up key selected",
						Toast.LENGTH_SHORT).show();
				return "up";

			}
		} else {
			if (distance < 200 * 200 && distance > 100 * 100) {
				Toast.makeText(MainPage.this, "down key selected",
						Toast.LENGTH_SHORT).show();
				return "down";

			}

		}
		return null;
	}

	public void chartInitialization() {
		mChart = (BarChart) findViewById(R.id.chart1);

		// enable the drawing of values
		mChart.setDrawYValues(true);

		mChart.setDrawValueAboveBar(true);

		mChart.setDescription("Financial Account");

		// if more than 60 entries are displayed in the chart, no values will be
		// drawn
		mChart.setMaxVisibleValueCount(400);

		// disable 3D
		mChart.set3DEnabled(false);

		// scaling can now only be done on x- and y-axis separately
		mChart.setPinchZoom(false);

		// draw shadows for each bar that show the maximum value
		// mChart.setDrawBarShadow(true);

		mChart.setUnit("unit of chart");

		// mChart.setDrawXLabels(false);

		mChart.setDrawGridBackground(false);
		mChart.setDrawHorizontalGrid(true);
		mChart.setDrawVerticalGrid(false);
		// mChart.setDrawYLabels(false);

		// sets the text size of the values inside the chart
		mChart.setValueTextSize(10f);

		mChart.setDrawBorder(false);
		// mChart.setBorderPositions(new BorderPosition[] {BorderPosition.LEFT,
		// BorderPosition.RIGHT});

		Typeface tf = Typeface.createFromAsset(getAssets(),
				"OpenSans-Regular.ttf");

		XLabels xl = mChart.getXLabels();
		xl.setPosition(XLabelPosition.BOTTOM);
		xl.setCenterXLabelText(true);
		xl.setTypeface(tf);

		YLabels yl = mChart.getYLabels();
		yl.setTypeface(tf);
		yl.setLabelCount(8);
		yl.setPosition(YLabelPosition.BOTH_SIDED);

		mChart.setValueTypeface(tf);

		setData(12, 50);
	}

	private void setData(int count, float range) {
		String[] mMonths = new String[] { "Jan", "Feb", "Mar", "Apr", "May",
				"Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec" };

		ArrayList<String> xVals = new ArrayList<String>();
		for (int i = 0; i < count; i++) {
			xVals.add(mMonths[i % 12]);
		}

		ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

		for (int i = 0; i < count; i++) {
			float mult = (range + 1);
			float val = (float) (Math.random() * mult);
			yVals1.add(new BarEntry(val, i));
		}

		BarDataSet set1 = new BarDataSet(yVals1, "DataSet");
		set1.setBarSpacePercent(35f);

		ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
		dataSets.add(set1);

		BarData data = new BarData(xVals, dataSets);

		mChart.setData(data);
	}

	public class MyView extends View {
		public MyView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void onDraw(Canvas canvas) {
			// TODO Auto-generated method stub
			super.onDraw(canvas);
			int x = getWidth();
			int y = getHeight();
			int radius;
			radius = 200;
			Paint paint = new Paint();
			paint.setStyle(Paint.Style.STROKE);
			paint.setColor(Color.WHITE);
			paint.setStrokeWidth(10);
			canvas.drawPaint(paint);
			// Use Color.parseColor to define HTML colors
			paint.setColor(Color.parseColor("#CD5C5C"));

			paint.setTextSize(25);

			canvas.drawCircle(x / 2, y / 2, radius, paint);

			canvas.drawCircle(x / 2, y / 2, radius / 2, paint);
			canvas.drawLine(x / 2 + radius / 2, y / 2, x / 2 + radius, y / 2,
					paint);
			canvas.drawLine(x / 2 - radius, y / 2, x / 2 - radius / 2, y / 2,
					paint);

			paint.setStyle(Style.FILL);
			paint.setColor(Color.BLACK);

			canvas.drawText("درآمدها", x / 2 - 35, y / 2 + 150, paint);
			canvas.drawText("هزینه های جاری", x / 2 - 85, y / 2 - 140, paint);

		}
	}

}