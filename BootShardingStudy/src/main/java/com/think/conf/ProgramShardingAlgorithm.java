package com.think.conf;

import java.util.Collection;
import java.util.LinkedHashSet;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;

/**
 * table 分片算法
 * 
 * @author
 *
 */
public class ProgramShardingAlgorithm implements SingleKeyTableShardingAlgorithm<Long> {

	public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<Long> shardingValue) {
		for (String each : availableTargetNames) {
			if (each.endsWith(shardingValue.getValue() % 2 + "")) {
				return each;
			}
		}
		throw new UnsupportedOperationException();
	}

	public Collection<String> doInSharding(Collection<String> availableTargetNames,
			ShardingValue<Long> shardingValue) {
		Collection<String> result = new LinkedHashSet<String>(availableTargetNames.size());
		Collection<Long> values = shardingValue.getValues();
		for (Long value : values) {
			for (String tableNames : availableTargetNames) {
				if (tableNames.endsWith(value % 2 + "")) {
					result.add(tableNames);
				}
			}
		}
		return result;
	}

	public Collection<String> doBetweenSharding(Collection<String> availableTargetNames,
			ShardingValue<Long> shardingValue) {
		Collection<String> result = new LinkedHashSet<String>(availableTargetNames.size());
		Range<Long> range = shardingValue.getValueRange();
		for (Long i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
			for (String each : availableTargetNames) {
				if (each.endsWith(i % 2 + "")) {
					result.add(each);
				}
			}
		}
		return result;
	}

}
