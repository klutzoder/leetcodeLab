package design;

public class Filter {

	public static void main(String[] args) {
		
	}


	public abstract class AbstractFilter {
		protected AbstractFilter nextFilter;

		public void SetNextFilter(AbstractFilter nextFilter){
			this.nextFilter = nextFilter;
		}

		public abstract void filter(String s);

		public abstract boolean needFilter(String s);

		public void doFilter(String s){
			if (needFilter(s)) {
				filter(s);
			} 
			if (nextFilter != null) nextFilter.doFilter(s);
		 }
	}


	public class TimeFilter extends AbstractFilter {

		@Override
		public void filter(String s) {
		}

		@Override
		public boolean needFilter(String s) {
			return false;
		}
	}

}