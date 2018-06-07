// Copyright 2018, OpenCensus Authors
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package io.ocintegrations.jedis;

import java.util.HashMap;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;

public class BenchmarkJedis {
    private static Jedis createJedis() {
        Jedis jedis = new Jedis("localhost");
        return jedis;
    }

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        Jedis jedis = createJedis();
    }

    @State(Scope.Thread)
    public static class ThreadState {
        Jedis jedis = createJedis();
    }

    /*
    @Benchmark
    public void testGetWithZeroLengthStringUnshared(ThreadState state) {
        state.jedis.get("benchmark-test");
    }
    */

    // ping
    @Benchmark
    public void benchmarkPing(BenchmarkState state) {
        state.jedis.ping("PING HERE FROM JEDIS");
    }
    // End ping

    // set
    @Benchmark
    public void benchmarkSetWithZeroLengthKeyZeroLengthStringShared(BenchmarkState state) {
        state.jedis.set("", "");
    }

    @Benchmark
    public void benchmarkSetWithShortStringShared(BenchmarkState state) {
        state.jedis.set("benchmark-test", "this is a value");
    }

    @Benchmark
    public void benchmarkSetWithMediumLengthValueShared(BenchmarkState state) {
        state.jedis.set("key", "thisisamediumlengthb7ec0269-000f-4b60-9508-2af93ed9cc37e20d4bf0-0d1c-4d77-8776-eebf4d27cc4be8e3c401-2e10-47f8-9ef7-a11a15d6e429a001848d-6c5d-4746-911f-1c0c42d5fed81601728b-0eb6-4896-8ee8-fcd5808ea904e6312db3-76c2-48df-960d-efc0ad43a6ca58a2546f-2ddf-43af-9af8-6706df176748");
    }

    @Benchmark
    public void benchmarkSetWithLongestStringShared(BenchmarkState state) {
        state.jedis.set("key", "b7ec0269-000f-4b60-9508-2af93ed9cc37e20d4bf0-0d1c-4d77-8776-eebf4d27cc4be8e3c401-2e10-47f8-9ef7-a11a15d6e429a001848d-6c5d-4746-911f-1c0c42d5fed81601728b-0eb6-4896-8ee8-fcd5808ea904e6312db3-76c2-48df-960d-efc0ad43a6ca58a2546f-2ddf-43af-9af8-6706df176748b7ec0269-000f-4b60-9508-2af93ed9cc37e20d4bf0-0d1c-4d77-8776-eebf4d27cc4be8e3c401-2e10-47f8-9ef7-a11a15d6e429a001848d-6c5d-4746-911f-1c0c42d5fed81601728b-0eb6-4896-8ee8-fcd5808ea904e6312db3-76c2-48df-960d-efc0ad43a6ca58a2546f-2ddf-43af-9af8-6706df176748553b681d");
    }
    // End set

    // get
    @Benchmark
    public void testGetWithZeroLengthStringShared(BenchmarkState state) {
        state.jedis.get("benchmark-test");
    }

    @Benchmark
    public void testGetWithShortStringShared(BenchmarkState state) {
        state.jedis.get("benchmark-test");
    }

    @Benchmark
    public void testGetWithMediumStringShared(BenchmarkState state) {
        state.jedis.get("thisisamediumlengthb7ec0269-000f-4b60-9508-2af93ed9cc37e20d4bf0-0d1c-4d77-8776-eebf4d27cc4be8e3c401-2e10-47f8-9ef7-a11a15d6e429a001848d-6c5d-4746-911f-1c0c42d5fed81601728b-0eb6-4896-8ee8-fcd5808ea904e6312db3-76c2-48df-960d-efc0ad43a6ca58a2546f-2ddf-43af-9af8-6706df176748");
    }

    @Benchmark
    public void testGetWithLongestStringShared(BenchmarkState state) {
        state.jedis.get("b7ec0269-000f-4b60-9508-2af93ed9cc37e20d4bf0-0d1c-4d77-8776-eebf4d27cc4be8e3c401-2e10-47f8-9ef7-a11a15d6e429a001848d-6c5d-4746-911f-1c0c42d5fed81601728b-0eb6-4896-8ee8-fcd5808ea904e6312db3-76c2-48df-960d-efc0ad43a6ca58a2546f-2ddf-43af-9af8-6706df176748b7ec0269-000f-4b60-9508-2af93ed9cc37e20d4bf0-0d1c-4d77-8776-eebf4d27cc4be8e3c401-2e10-47f8-9ef7-a11a15d6e429a001848d-6c5d-4746-911f-1c0c42d5fed81601728b-0eb6-4896-8ee8-fcd5808ea904e6312db3-76c2-48df-960d-efc0ad43a6ca58a2546f-2ddf-43af-9af8-6706df176748553b681d");
    }
    // End get

    // exists
    @Benchmark
    public void benchmarkExists(BenchmarkState state) {
        state.jedis.exists("foo", "bar", "jedis", "this is the other one right here");
    }
    // End exists


    // Del
    @Benchmark
    public void benchmarkDel(BenchmarkState state) {
        state.jedis.del("user-fd7abacb-f9ed-4ffb-8b89-6f92aea60cee",
                        "user-34556976-a8df-4855-bae3-25bdf4d0fd25",
                        "user-37a50ff3-8b5a-4aa7-b267-d7f40fc6b47ad",
                        "user-3112d4a9-85f7-4e36-9b35-821dc44eb536");
    }
    // End Del

    // Unlink
    @Benchmark
    public void benchmarkUnlink(BenchmarkState state) {
        state.jedis.unlink("links-57450c0b-4dd7-42c5-a102-023b5b4435ec",
                           "links-3db5bdfb-2a4e-43df-b8b5-dc3a015eda9c");
    }
    // EndUnlink

    // Type
    @Benchmark
    public void benchmarkType(BenchmarkState state) {
        state.jedis.type("foo-bar-baz-twaz");
    }
    // End Type

    // Keys
    @Benchmark
    public void benchmarkKeys(BenchmarkState state) {
        state.jedis.keys("user-fd7abacb-f9ed-4ffb-8b89-6f92aea60cee");
    }
    // End Keys

    // RandomKey
    @Benchmark
    public void benchmarkRandomKey(BenchmarkState state) {
        state.jedis.randomKey();
    }
    // End RandomKey

    // Rename
    @Benchmark
    public void benchmarkRename(BenchmarkState state) {
        state.jedis.rename("user-24693c14-ef0e-45c4-8069-bd461f16a906",
                           "user-d835fe1d-9240-4d65-8191-96367eb605ff");
    }
    // End Rename

    // renamenx
    @Benchmark
    public void benchmarkRenameNx(BenchmarkState state) {
        state.jedis.renamenx("user-24693c14-ef0e-45c4-8069-bd461f16a906",
                             "user-d835fe1d-9240-4d65-8191-96367eb605ff");
    }
    // End renamenx

    // expire
    @Benchmark
    public void benchmarkExpire(BenchmarkState state) {
        state.jedis.expire("user-24693c14-ef0e-45c4-8069-bd461f16a906", 34);
    }
    // End expire

    // expireAt
    @Benchmark
    public void benchmarkExpireAt(BenchmarkState state) {
        state.jedis.expire("user-24693c14-ef0e-45c4-8069-bd461f16a906", 1528368991);
    }
    // End expire

    // ttl
    @Benchmark
    public void benchmarkTTL(BenchmarkState state) {
        state.jedis.ttl("user-24693c14-ef0e-45c4-8069-bd461f16a906");
    }
    // End ttl

    // touch
    @Benchmark
    public void benchmarkTouch(BenchmarkState state) {
        state.jedis.touch("user-fd7abacb-f9ed-4ffb-8b89-6f92aea60cee",
                          "user-34556976-a8df-4855-bae3-25bdf4d0fd25",
                          "user-37a50ff3-8b5a-4aa7-b267-d7f40fc6b47ad",
                          "user-3112d4a9-85f7-4e36-9b35-821dc44eb536");
    }
    // End touch

    // move
    @Benchmark
    public void benchmarkMove(BenchmarkState state) {
        state.jedis.move("user-fd7abacb-f9ed-4ffb-8b89-6f92aea60cee", 17);
    }
    // End move

    // getSet
    @Benchmark
    public void benchmarkGetSet(BenchmarkState state) {
        state.jedis.getSet("user-fd7abacb-f9ed-4ffb-8b89-6f92aea60cee", "Arizona");
    }
    // End getSet

    // mget
    @Benchmark
    public void benchmarkMGet(BenchmarkState state) {
        state.jedis.mget("user-fd7abacb-f9ed-4ffb-8b89-6f92aea60cee",
                         "user-34556976-a8df-4855-bae3-25bdf4d0fd25",
                         "user-37a50ff3-8b5a-4aa7-b267-d7f40fc6b47ad",
                         "user-3112d4a9-85f7-4e36-9b35-821dc44eb536");
    }
    // End mget

    // setnx
    @Benchmark
    public void benchmarkSetnx(BenchmarkState state) {
        state.jedis.setnx("user-fd7abacb-f9ed-4ffb-8b89-6f92aea60cee", "redis-cluster");
    }
    // End setnx
 
    // setex
    @Benchmark
    public void benchmarkSetex(BenchmarkState state) {
        state.jedis.setex("user-fd7abacb-f9ed-4ffb-8b89-6f92aea60cee", 2, "redis-cluster");
    }
    // End setex
 
    // mset
    @Benchmark
    public void benchmarkMSet(BenchmarkState state) {
        state.jedis.mset("user-fd7abacb-f9ed-4ffb-8b89-6f92aea60cee", "Arizona",
                         "user-34556976-a8df-4855-bae3-25bdf4d0fd25", "California",
                         "user-37a50ff3-8b5a-4aa7-b267-d7f40fc6b47ad", "Alberta",
                         "user-3112d4a9-85f7-4e36-9b35-821dc44eb536", "Tripoli");
    }
    // End mset

    // msetnx
    @Benchmark
    public void benchmarkMSetNx(BenchmarkState state) {
        state.jedis.msetnx("user-fd7abacb-f9ed-4ffb-8b89-6f92aea60cee", "Arizona",
                         "user-34556976-a8df-4855-bae3-25bdf4d0fd25", "California",
                         "user-37a50ff3-8b5a-4aa7-b267-d7f40fc6b47ad", "Alberta",
                         "user-3112d4a9-85f7-4e36-9b35-821dc44eb536", "Tripoli");
    }
    // End mset

    // decrBy
    @Benchmark
    public void benchmarkDecrBy(BenchmarkState state) {
        state.jedis.decrBy("user-24693c14-ef0e-45c4-8069-bd461f16a906", 3);
    }
    // End decrBy

    // decr
    @Benchmark
    public void benchmarkDecr(BenchmarkState state) {
        state.jedis.decr("user-24693c14-ef0e-45c4-8069-bd461f16a906");
    }
    // End decr

    // incrBy
    @Benchmark
    public void benchmarkIncrBy(BenchmarkState state) {
        state.jedis.incrBy("user-24693c14-ef0e-45c4-8069-bd461f16a906", 3);
    }
    // End incrBy

    // incrByFloat
    @Benchmark
    public void benchmarkIncrByFloat(BenchmarkState state) {
        state.jedis.incrByFloat("user-24693c14-ef0e-45c4-8069-bd461f16a906", 17.9);
    }
    // End incrByFloat

    // incr
    public void benchmarkIncr(BenchmarkState state) {
        state.jedis.incr("user-24693c14-ef0e-45c4-8069-bd461f16a906");
    }
    // End incr

    // append
    public void benchmarkAppend(BenchmarkState state) {
        state.jedis.append("user-24693c14-ef0e-45c4-8069-bd461f16a906", "Alabama");
    }
    // End append

    // substr
    public void benchmarkSubstr(BenchmarkState state) {
        state.jedis.substr("user-24693c14-ef0e-45c4-8069-bd461f16a906", 10, 29);
    }
    // End substr

    // hset
    public void benchmarkHSet(BenchmarkState state) {
        state.jedis.hset("last_station", "user-24693c14-ef0e-45c4-8069-bd461f16a906", "Berkeley");
    }
    // End hset

    // hget
    public void benchmarkHGet(BenchmarkState state) {
        state.jedis.hget("last_station", "user-24693c14-ef0e-45c4-8069-bd461f16a906");
    }
    // End hget

    // hsetnx
    public void benchmarkHSetNx(BenchmarkState state) {
        state.jedis.hsetnx("last_station", "user-24693c14-ef0e-45c4-8069-bd461f16a906", "Berkeley");
    }
    // End hsetnx

    // hmset
    public void benchmarkHMSet(BenchmarkState state) {
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("user-0d933409-dcee-48da-b73b-8dee6070d567", "Civic Station");
        hm.put("user-cdd5168a-8027-4b38-8408-8e4c24fb4a25", "Embarcadero");
        hm.put("user-82e3f0b5-fa5a-415e-b007-e3c5c627ea9c", "Millbrae");
        hm.put("user-24693c14-ef0e-45c4-8069-bd461f16a906", "Berkeley");
        state.jedis.hmset("last_station", hm);
    }
    // End hmset

    // hmget
    public void benchmarkHMGet(BenchmarkState state) {
        state.jedis.hmget("user-0d933409-dcee-48da-b73b-8dee6070d567",
                          "user-cdd5168a-8027-4b38-8408-8e4c24fb4a25",
                          "user-82e3f0b5-fa5a-415e-b007-e3c5c627ea9c",
                          "user-24693c14-ef0e-45c4-8069-bd461f16a906");
    }
    // End hmget

    // hincrBy
    public void benchmarkHIncrBy(BenchmarkState state) {
        state.jedis.hincrBy("quotes", "033357eb-f8c5-417a-b3b3-4e70c8059ea9", 39);
    }
    // End hincrBy

    // hincrByFloat
    public void benchmarkHIncrByFloat(BenchmarkState state) {
        state.jedis.hincrByFloat("error_rates", "068b8f7b-a7d0-4333-8f48-782096ea3e99", 0.0783);
    }
    // End hincrByfloat

    // hexists
    public void benchmarkHExists(BenchmarkState state) {
        state.jedis.hexists("error_rates", "d5dd1d17-4d29-4fd3-aa85-850e580d4a43");
    }
    // End hexists

    // hdel
    public void benchmarkHDel(BenchmarkState state) {
        state.jedis.hdel("error_rates", "d5dd1d17-4d29-4fd3-aa85-850e580d4a43",
                                        "ecb784f4-f34f-481c-b7c9-624cab985b6b",
                                        "32d2eba0-a790-47c6-8500-d4d058971877");
    }
    // End hdel

    // hlen
    public void benchmarkHLen(BenchmarkState state) {
        state.jedis.hlen("error_rates");
    }
    // End hlen

    // hkeys
    public void benchmarkHKeys(BenchmarkState state) {
        state.jedis.hkeys("error_rates");
    }
    // End hkeys

    // hvals
    public void benchmarkHVals(BenchmarkState state) {
        state.jedis.hvals("error_rates");
    }
    // End hvals

    // hgetAll
    public void benchmarkHGetAll(BenchmarkState state) {
        state.jedis.hgetAll("error_rates");
    }
    // End hgetAll

    // lpush
    public void benchmarkLPush(BenchmarkState state) {
        state.jedis.lpush("quotes", "e928404f-3c1a-421a-a800-853de32007b7",
                                    "58bc9f6e-e332-4439-97f6-000fefd4ece1",
                                    "30e86283-8610-4463-aaaf-a952ffd83959",
                                    "33aeeec8-2b39-47ac-9c3f-23383868c7a3");
    }
    // End lpush

    // rpush
    public void benchmarkRPush(BenchmarkState state) {
        state.jedis.rpush("quotes", "e928404f-3c1a-421a-a800-853de32007b7",
                                    "58bc9f6e-e332-4439-97f6-000fefd4ece1",
                                    "30e86283-8610-4463-aaaf-a952ffd83959",
                                    "33aeeec8-2b39-47ac-9c3f-23383868c7a3");
    }
    // End rpush

    // llen
    public void benchmarkLLen(BenchmarkState state) {
        state.jedis.llen("checkins");
    }
    // End llen

    // lrange
    public void benchmarkLRange(BenchmarkState state) {
        state.jedis.lrange("checkins", 49, 91);
    }
    // End lrange

    // ltrim
    public void benchmarkLTrim(BenchmarkState state) {
        state.jedis.ltrim("checkins", 67, 81);
    }
    // End ltrim

    // lindex
    public void benchmarkLIndex(BenchmarkState state) {
        state.jedis.lindex("checkins", 4);
    }
    // End lindex

    // lset
    public void benchmarkLSet(BenchmarkState state) {
        state.jedis.lset("checkins", 4, "81668e1c-b234-4235-a246-bf6e180e6103");
    }
    // End lset

    // lrem
    public void benchmarkLRem(BenchmarkState state) {
        state.jedis.lrem("checkins", 10, "81668e1c-b234-4235-a246-bf6e180e6103");
    }
    // End lrem

    // lpop
    public void benchmarkLPop(BenchmarkState state) {
        state.jedis.lpop("checkins");
    }
    // End lpop

    // rpop
    public void benchmarkRPop(BenchmarkState state) {
        state.jedis.rpop("checkins");
    }
    // End rpop

    // rpoplpush
    public void benchmarkRPopLPush(BenchmarkState state) {
        state.jedis.rpoplpush("checkins", "fee28678-f84e-4961-b81e-19b9959fb3c2");
    }
    // End rpoplpush

    // sadd
    public void benchmarkSAdd(BenchmarkState state) {
        state.jedis.sadd("tracks", "993f93f5-032c-4c2f-8f42-2c9049f1a33d",
                                   "f171725a-825e-4782-a510-f6a15b1a70c1",
                                   "19ee233d-4d27-4794-b0e2-3aa8c9b73e1b",
                                   "d321210c-4fce-417d-9b3f-e080fdf0d46b");
    }
    // End sadd

    // smembers
    public void benchmarkSMembers(BenchmarkState state) {
        state.jedis.smembers("tracks");
    }
    // End smembers

    // srem
    public void benchmarkSRem(BenchmarkState state) {
        state.jedis.srem("tracks", "993f93f5-032c-4c2f-8f42-2c9049f1a33d",
                                   "f171725a-825e-4782-a510-f6a15b1a70c1",
                                   "19ee233d-4d27-4794-b0e2-3aa8c9b73e1b",
                                   "d321210c-4fce-417d-9b3f-e080fdf0d46b");
    }
    // End srem

    // spop
    public void benchmarkSPop(BenchmarkState state) {
        state.jedis.spop("tracks");
    }
    // End spop

    // spop_withCount
    public void benchmarkSPopWithCount(BenchmarkState state) {
        state.jedis.spop("tracks", 10);
    }
    // End spop_withCount

    // smove
    public void benchmarkSMove(BenchmarkState state) {
        state.jedis.smove("tracks", "3978e5ef-5245-456f-9b7e-32febf9b017a", "a0959b03-0b85-4f26-8205-e4bcdfc14170");
    }
    // End smove

    // scard
    public void benchmarkSCard(BenchmarkState state) {
        state.jedis.scard("tracks");
    }
    // End scard

    // sismember
    public void benchmarkSIsMember(BenchmarkState state) {
        state.jedis.sismember("tracks", "deb3ce83-b4f4-4f28-bc6f-6b5ccea6cbb1");
    }
    // End sismember

    // sinter
    public void benchmarkSInter(BenchmarkState state) {
        state.jedis.sinter("tracks", "cleaned");
    }
    // End sinter

    // sinterstore
    public void benchmarkSInterStore(BenchmarkState state) {
        state.jedis.sinterstore("tracks-cleaned", "a4bc16a5-21fc-4c94-97e3-39a813e77f46",
                                                  "72b08d1e-b9cc-4b46-a6a9-82b7ad3dfdac",
                                                  "431c59b8-c369-4360-b553-21e4109c67cd");
    }
    // End sinterstore

    // sunion
    public void benchmarkSUnion(BenchmarkState state) {
        state.jedis.sunion("fdb3fefa-c2da-4643-abda-e34bba4b4453",
                           "a4bc16a5-21fc-4c94-97e3-39a813e77f46",
                           "72b08d1e-b9cc-4b46-a6a9-82b7ad3dfdac",
                           "431c59b8-c369-4360-b553-21e4109c67cd");
    }
    // End sinterstore
 
    // pubsubChannels
    public void benchmarkPubsubChannels(BenchmarkState state) {
        state.jedis.pubsubChannels("webhook-");
    }
    // End pubsubChannels

    // pfcount
    public void benchmarkPFCount(BenchmarkState state) {
        state.jedis.pfcount("Four Seasons", "Hyatt");
    }
    // End pfcount

    // pfmerge
    @Benchmark
    public void benchmarkPFMerge(BenchmarkState state) {
        state.jedis.pfmerge("hotels", "Four Seasons", "Hyatt");
    }
    // End pfmerge

    // blpop
    @Benchmark
    public void benchmarkBLPop(BenchmarkState state) {
        state.jedis.blpop(7, "checkins");
    }
    // End blpop

    // brpop
    @Benchmark
    public void benchmarkBRPop(BenchmarkState state) {
        state.jedis.brpop(7, "checkins");
    }
    // End brpop

    // geoadd
    @Benchmark
    public void benchmarkGeoAdd(BenchmarkState state) {
        state.jedis.geoadd("tiles", 47.98, -78.17, "swamp");
    }
    // End geoadd

    // geodist
    @Benchmark
    public void benchmarkGeoDist(BenchmarkState state) {
        state.jedis.geodist("tiles", "swamp", "building8");
    }
    // End geodist

    // geohash
    @Benchmark
    public void benchmarkGeoHash(BenchmarkState state) {
        state.jedis.geohash("tiles", "swamp");
    }
    // End geohash

    // geopos
    @Benchmark
    public void benchmarkGeoPos(BenchmarkState state) {
        state.jedis.geopos("tiles", "swamp");
    }
    // End geopos

    // georadius
    @Benchmark
    public void benchmarkGeoRadius(BenchmarkState state) {
        state.jedis.georadius("tiles", 39, -53.793, 500.7, GeoUnit.KM);
    }
    // End georadius

    // georadiusByMember
    @Benchmark
    public void benchmarkGeoRadiusByMember(BenchmarkState state) {
        state.jedis.georadiusByMember("tiles", "blast", 500.7, GeoUnit.KM);
    }
    // End georadiusByMember

    // moduleLoad
    @Benchmark
    public void benchmarkModuleLoad(BenchmarkState state) {
        state.jedis.moduleLoad("aModule");
    }
    // End moduleLoad

    // moduleUnload
    @Benchmark
    public void benchmarkModuleUnload(BenchmarkState state) {
        state.jedis.moduleUnload("aModule");
    }
    // End moduleUnload

    // moduleList
    @Benchmark
    public void benchmarkModuleList(BenchmarkState state) {
        state.jedis.moduleList();
    }
    // End moduleList

    // bitfield
    @Benchmark
    public void benchmarkBitfield(BenchmarkState state) {
        state.jedis.bitfield("coordinates", "OVERFLOW", "FAIL", "incrby", "u2", "102", "1");
    }
    // End bitfield

    // hstrlen
    @Benchmark
    public void benchmarkHStrlen(BenchmarkState state) {
        state.jedis.hstrlen("orders", "fdb3fefa-c2da-4643-abda-e34bba4b4453");
    }
    // End hstrlen
}
