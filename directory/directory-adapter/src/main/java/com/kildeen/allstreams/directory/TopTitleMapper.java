package com.kildeen.allstreams.directory;

class TopTitleMapper {

    TopTitleDTO map(TopTitle topTitle) {
        return new TopTitleDTO(topTitle.id(), topTitle.title(), topTitle.views());
    }
}
