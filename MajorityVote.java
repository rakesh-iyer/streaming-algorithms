class MajorityVote {
	static class VoteTuple {
		int id;
		int count;
	}

	int findMajority(int ids[]) {
		VoteTuple majority = new VoteTuple();
		for (int id : ids) {
			if (id == majority.id) {
				majority.count++;
			} else if (majority.count > 0) {
				majority.count--;
			} else {
				majority.id = id;
				majority.count++;
			}
		}

		return majority.id;
	}

	public static void main(String args[]) {
		MajorityVote mv = new MajorityVote();

		int a[] = {1,1,1,3,2,1,1,1,2,2,2,1};

		System.out.println(mv.findMajority(a));
	}
}
