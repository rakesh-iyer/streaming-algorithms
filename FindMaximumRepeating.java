import java.util.*;

class FindMaximumRepeating {
	long findMaximumRepeating(long ids[], int maxSize) {
		Map<Long, Long> map = new HashMap<>();

		for (long id : ids) {
			Long count = map.get(id);
			if (count != null) {
				map.put(id, count+1);
			} else if (map.size() < maxSize) {
				map.put(id, 1L);
			} else {
				// iterate through map and decrement count by 1.
				for (Iterator<Map.Entry<Long, Long>> iter = map.entrySet().iterator(); iter.hasNext();) {
					Map.Entry<Long, Long> entry = iter.next();
					Long value = entry.getValue();
					if (value > 1) {
						entry.setValue(value-1);
					} else {
						iter.remove();
					}
				}
			}
		}

		long id = Long.MIN_VALUE;
		long maxValue = Long.MIN_VALUE;
		for (Map.Entry<Long, Long> entry : map.entrySet()) {
			if (entry.getValue() > maxValue) {
				id = entry.getKey();
				maxValue = entry.getValue();
			}
		}

		return id;
	}

	public static void main(String args[]) {
		FindMaximumRepeating fmr = new FindMaximumRepeating();
		long ids[] = {};

		System.out.println(fmr.findMaximumRepeating(ids, 1));
	}
}
