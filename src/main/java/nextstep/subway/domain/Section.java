package nextstep.subway.domain;

import lombok.Getter;
import org.jgrapht.graph.DefaultWeightedEdge;

import javax.persistence.*;

@Getter
@Entity
public class Section extends DefaultWeightedEdge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "line_id")
    private Line line;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "up_station_id")
    private Station upStation;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "down_station_id")
    private Station downStation;

    private int distance;
    private int duration;

    public Section() {

    }

    public Section(Line line, Station upStation, Station downStation, int distance, int duration) {
        validatePositive(distance);
        validatePositive(duration);

        this.line = line;
        this.upStation = upStation;
        this.downStation = downStation;
        this.distance = distance;
        this.duration = duration;
    }

    private void validatePositive(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("양수가만 입력가능 합니다 " + number);
        }
    }

    public boolean isSameUpStation(Station station) {
        return this.upStation == station;
    }

    public boolean isSameDownStation(Station station) {
        return this.downStation == station;
    }

    public boolean hasDuplicateSection(Station upStation, Station downStation) {
        return (this.upStation == upStation && this.downStation == downStation)
                || (this.upStation == downStation && this.downStation == upStation);
    }

    public int minusDuration(int duration) {
        return this.duration - duration;
    }

    public int minusDistance(int distance) {
        return this.distance - distance;
    }

    public int sumDistance(int distance) {
        return this.distance + distance;
    }

    public int sumDuration(int duration) {
        return this.duration + duration;
    }
}