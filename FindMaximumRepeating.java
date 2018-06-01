import java.util.*;

class HeavyHitters {
	Map<Long,Long> findHeavyHittersMap(long ids[], int maxSize) {
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

		return map;
	}

	List<Long> topN(long ids[], int n) {
		class Entry {
			Long id;
			Long value;

			Entry(Long id, Long value) {
				this.id = id;
				this.value = value;
			}

			// reverse comparator to get max heap
			class EntryComparator implements Comparator<Entry> {
				public int compare(Entry a, Entry b) {
					if (b.value > a.value) {
						return 1;
					} else if (b.value < a.value) {
						return -1;
					} else {
						return 0;
					}
				}
			}
		}

		Map<Long,Long> map = findHeavyHittersMap(ids, n*5);

		// put the map into heap and remove top n elements.
		PriorityQueue<Entry> pQueue = new PriorityQueue<>(1, new Entry(0L, 0L).new EntryComparator());

		for (Map.Entry<Long, Long> entry : map.entrySet()) {
			Entry e  = new Entry(entry.getKey(), entry.getValue());
			pQueue.add(e);
		}

		Entry e;
		int i = 0;
		List<Long> list = new ArrayList<>();
		while ((e = pQueue.poll()) != null && i < n) {
			list.add(e.id);
			i++;
		}

		return list;
	}

	public static void main(String args[]) {
		HeavyHitters hitters = new HeavyHitters();
		long ids[] = {1,2,3,2,1,3,2,3};

		for (Long id : hitters.topN(ids, 2)) {
			System.out.println(id);
		}
	}
}
