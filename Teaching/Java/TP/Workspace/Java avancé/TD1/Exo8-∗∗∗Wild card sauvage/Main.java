public class Main {
	public static void main (String[] args) {
		Wild<A> wa=null;
		Wild<B> wb=null;
		Wild<? super A> wsa=null;
		Wild<? super B> wsb=null;
		Wild<? extends A> wea=null;
		Wild<? extends B> web=null;

		wa.sup(wsa);
		wa.ext(wea);

		//wsa.sup(web);
		wsa.ext(wea);

		wea.sup(wsa);
		//wea.ext(wsa);

		wb.sup(wsb);
		wb.ext(web);

		//wsb.sup(web);
		wsb.ext(web);

		web.sup(wsb);
		//web.ext(wsb);

	}
}
