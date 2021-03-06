package logic;



/**
 * 
 * This class holds a word and all it's related data.
 * Like it's positive and negative semantic score,
 * possibly meaning or it's context.
 * 
 * @author croo
 *
 */
public class Word {
	
	private String word;
	private Double positiveWeight; 
	private Double negativeWeight;
	
	public Word(String word) {
		this.word = word;
	}
	
	public Double getPositiveWeight() {
		return positiveWeight;
	}
	
	public void setPositiveWeight(Double average) {
		positiveWeight = average;
	}

	public Double getNegativeWeight() {
		return negativeWeight;
	}
	
	public void setNegativeWeight(Double average) {
		negativeWeight = average;
	}
	
	public Double getPositiveNormalizedWeight() {
		return (positiveWeight + negativeWeight == 0) ? 0:positiveWeight / (positiveWeight + negativeWeight);
	}
	
	public Double getNegativeNormalizedWeight() {
		return (positiveWeight + negativeWeight == 0) ? 0:negativeWeight / (positiveWeight + negativeWeight);
	}
	
	@Override
	public String toString() {
		return word;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Word other = (Word) obj;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}

	public Double getBayesianPositiveWeight() {
		return positiveWeight;
	}

	public Double getBayesianNegativeWeight() {
		return negativeWeight;
	}

	public void setBayesianWeight(Double probability, Classification label) {
		if(label == Classification.POSITIVE) {
			this.positiveWeight = probability;
		} else {
			this.negativeWeight = probability;
		}
	}
}
